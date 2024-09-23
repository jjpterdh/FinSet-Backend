package board.mapper;

import board.domain.BoardAttachmentVO;
import board.domain.BoardVO;
import common.pagination.PageRequest;

import java.util.List;

public interface BoardMapper {
    int getTotalCount();
    List<BoardVO> getPage(PageRequest pageRequest);
    //    @Select("select * from tbl_board order by no desc")
    public List<BoardVO> getList();

    public BoardVO get(Long no);

    public void create(BoardVO board);

    public int update(BoardVO board);
    public int delete(Long bno);



    public void createAttachment(BoardAttachmentVO attach);

    public List<BoardAttachmentVO> getAttachmentList(Long bno);

    public BoardAttachmentVO getAttachment(Long No);
    public int deleteAttachment(Long no);;


}



