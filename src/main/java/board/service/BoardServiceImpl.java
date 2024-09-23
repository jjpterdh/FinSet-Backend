package board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import board.domain.BoardAttachmentVO;
import board.domain.BoardVO;
import board.dto.BoardDTO;
import board.mapper.BoardMapper;
import common.pagination.Page;
import common.pagination.PageRequest;
import common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static common.util.UploadFiles.upload;

@Log4j
@Service // 서비스 역할하는 빈등록
@RequiredArgsConstructor // final 필드로 생성자 생성자 추가
public class BoardServiceImpl implements BoardService{
    //생성자가 하나라면 주입가능
    final private BoardMapper mapper;
    private final static String BASE_DIR="c:/upload/board";

    @Override
    public Page<BoardDTO> getPage(PageRequest pageRequest) {
        List<BoardVO> boards= mapper.getPage(pageRequest);
        int totalCount = mapper.getTotalCount();

        return Page.of(pageRequest,totalCount,boards.stream().map(BoardDTO::of).toList());

    }

    @Override
    public List<BoardDTO> getList() {
       log.info("getList.......");
       return mapper.getList().stream() //BoardVO의 스트림
               .map(BoardDTO::of) //전부 BoardDTO로 변환-> BoardDTO의 스트림
               .toList(); //BoardDTO의 리스트로 변환
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get......."+no);
        BoardDTO board= BoardDTO.of(mapper.get(no));
        //board 객체가 null이면 NoSuchElementException 예외 발생, null이 아니면 해당 객체 반환
        return Optional.ofNullable(board)
                .orElseThrow(NoSuchElementException::new);

    }
@Transactional
    @Override
    public BoardDTO create(BoardDTO board) {
  log.info("create......"+board);
        BoardVO boardVo=board.toVo();
  mapper.create(boardVo);

  List<MultipartFile>files = board.getFiles();
  if (files !=null&&!files.isEmpty()) {
      upload(boardVo.getNo(),files);
  }
return get(boardVo.getNo());
    }

    @Override
    public BoardDTO update(BoardDTO board) {
    log.info("update....."+board);
    BoardVO boardVo=board.toVo();
     log.info("update......"+boardVo);
    mapper.update(boardVo);

    List<MultipartFile> files=board.getFiles();
    if (files!=null&&!files.isEmpty()) {
        upload(board.getNo(),files);
    }
    return get(board.getNo());
            }

    @Override
    public BoardDTO delete(Long no) {
        log.info("delete......"+no);
        BoardDTO board=get(no);
        mapper.delete(no);
        return board;
    }




    private void upload(Long bno, List<MultipartFile> files) {
        for(MultipartFile part:files) {
            if (part.isEmpty()) continue;
            try {
                String uploadPath = UploadFiles.upload(BASE_DIR, part);
                BoardAttachmentVO attach=BoardAttachmentVO.of(part,bno,uploadPath);
                mapper.createAttachment(attach);

            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no)==1;
    }
}
