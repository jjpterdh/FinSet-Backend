from flask import Flask, request, jsonify
from flask_cors import CORS
import mysql.connector
import openai
import json
import re

app = Flask(__name__)
CORS(app)

# OpenAI API 키 설정
openai.api_key = 'YOUR_API_KEY'

# db 연결
def get_connection():
    return mysql.connector.connect(
        host='localhost',
        user='root',
        password='YOUR_DB_PW',
        database='finset'
    )

# sql 파일 읽는 함수
def read_sql_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        sql_text = file.read()
    return sql_text

# 테이블과 조건을 매핑하는 함수
def map_conditions_to_tables(tables_str, conditions_str, sql_text):
    tables = [table.strip() for table in tables_str.split(',')]
    conditions = [condition.strip() for condition in conditions_str.split(',')]
    
    table_columns = {}
    for table in re.findall(r'CREATE TABLE\s+`?(\w+)`?\s*\((.*?)\);', sql_text, re.S):
        table_name = table[0].strip()
        columns = [col.strip().split()[0].strip('`') for col in table[1].split(',') if col]
        table_columns[table_name] = columns

    table_condition_mapping = {}
    for table in tables:
        applicable_conditions = []
        for condition in conditions:
            for column in table_columns.get(table, []):
                if column in condition:
                    applicable_conditions.append(condition)
                    break
        if applicable_conditions:
            table_condition_mapping[table] = applicable_conditions

    return table_condition_mapping

# 테이블과 조건을 활용해 sql문 생성
def text2sql(table_conditions):
    sql_queries = []
    for table, conditions in table_conditions.items():
        query = f"SELECT * FROM {table}"
        if conditions:
            where_clause = " AND ".join(conditions)
            query += f" WHERE {where_clause}"
        sql_queries.append(query)
    return sql_queries

@app.route('/execute', methods=['POST'])
def execute_query():
    data = request.json
    query = data.get('query')

    connection = get_connection()
    cursor = connection.cursor(dictionary=True)

    # SQL 파일 경로
    file_path = "./FinSetTABLE.sql"
    sql_text = read_sql_file(file_path)

    functions = [
        {
            "name": "text2sql",
            "description": "주어진 문장을 조건에 맞게 단계별로 생각하여 SQL문으로 바꿔서 문자열로 반환하기.",
            "parameters": {
                "type": "object",
                "properties": {
                    "table": {
                        "type": "string",
                        "description": "CREATE TABLE문을 통해 어떤 테이블이 존재하고 어떤 구조인지 파악합니다. " + 
                                        "이 필드는 한개 이상의 테이블을 추출할 수 있습니다. " +
                                        "예시 1: '변동률이 양수인 펀드와 주식을 보여줘' -> ['tbl_fund', 'tbl_stock']\n" +
                                        "예시 2: '단리인 예적금 상품을 모두 보여줘' -> ['tbl_deposit', 'tbl_installment']\n" + sql_text,
                    },
                    "query": {
                        "type": "string",
                        "description": "user가 입력한 문장"
                    },
                    "conditions": {
                        "type": "string",
                        "description": "조건으로 사용할 수 있는 요소를 추출한 결과입니다. " +
                                        "사용자의 쿼리에서 숫자, 비교 연산자, 그리고 관련 속성(예: 주식 가격)을 추출하여 " +
                                        "각 테이블과 매핑되는 SQL 조건문 형식으로 변환합니다. " +
                                        "예를 들어, 사용자가 '변동률이 양수인 펀드와 주식을 보여줘'라고 요청한 경우, " + 
                                        "fund_change_rate > 0', 'price_change_rate > 0'와 같은 형식으로 반환됩니다." + sql_text
                    },
                    "keyword": {
                        "type": "string",
                         "description": "user가 입력한 문장을 요약할 수 있는 키워드를 추출합니다. " +
                                        "키워드는 단어 형식으로 추출됩니다. 키워드는 1개 이상의 단어를 추출할 수 있습니다. "
                    }
                },
                "required": ["table", "query", "conditions", "keyword"],
            },
        }
    ]

    messages = [{"role": "user", "content": query}]

    response = openai.chat.completions.create(
        model="gpt-4-0613",
        messages=messages,
        functions=functions,
        function_call={"name": "text2sql"}
    )

    function_call_arguments = json.loads(response.choices[0].message.function_call.arguments)
    table = function_call_arguments['table']
    condition = function_call_arguments['conditions']
    keyword = function_call_arguments['keyword']  # 키워드 추가

    mapping_result = map_conditions_to_tables(table, condition, sql_text)
    sql_queries = text2sql(mapping_result)

    query_results = []  # 쿼리 결과를 저장할 리스트

    for sql_query in sql_queries:
        cursor.execute(sql_query)
        results = cursor.fetchall()
        query_results.append(results)

    cursor.close()
    connection.close()

    return jsonify({
        'queries': sql_queries,  # 리스트 형태로 그대로 전달
        'keyword': keyword,
        'result': query_results,
    })

if __name__ == '__main__':
    app.run(port=5000)
