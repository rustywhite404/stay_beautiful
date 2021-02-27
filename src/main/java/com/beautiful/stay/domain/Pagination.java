package com.beautiful.stay.domain;

public class Pagination {

    // 페이지 당 보여지는 게시글의 최대 개수
    private int pageSize = 2;

    // 페이징 된 버튼의 블럭당 최대 개수
    private int blockSize = 3;

    // 현재 페이지
    private int page = 1;

    // 현재 블럭
    private int block = 1;

    // 총 게시글 수
    private int totalListCnt;

    // 총 페이지 수
    private int totalPageCnt;

    // 총 블럭 수
    private int totalBlockCnt;

    // 블럭 시작 페이지
    private int startPage = 1;

    // 블럭 마지막 페이지
    private int endPage = 1;

    // DB에서 어디에서부터 SELECT 할 지
    private int startIndex = 0;

    // 이전 블럭의 마지막 페이지
    private int preBlock;

    // 다음 블럭의 시작 페이지
    private int nextBlock;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getTotalListCnt() {
        return totalListCnt;
    }

    public void setTotalListCnt(int totalListCnt) {
        this.totalListCnt = totalListCnt;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public void setTotalPageCnt(int totalPageCnt) {
        this.totalPageCnt = totalPageCnt;
    }

    public int getTotalBlockCnt() {
        return totalBlockCnt;
    }

    public void setTotalBlockCnt(int totalBlockCnt) {
        this.totalBlockCnt = totalBlockCnt;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPreBlock() {
        return preBlock;
    }

    public void setPreBlock(int preBlock) {
        this.preBlock = preBlock;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    public Pagination(int totalListCnt, int page) {
        // 현재 페이지 세팅
        setPage(page);

        // 총 게시글 수 세팅
        setTotalListCnt(totalListCnt);
        if(totalListCnt == 0){ // 총 게시글이 0일때 시작페이지 1, 끝페이지 1, 현재페이지 1, 총 페이지수 1 반환시키고 종료
            setEndPage(1);
            setPage(1);
            setTotalPageCnt(1);
            return;
        }

        // 총 페이지 수
        setTotalPageCnt((int)Math.ceil((double) (totalListCnt)/pageSize));

        // 총 블럭 수
        setTotalBlockCnt((int)Math.ceil((double) (totalPageCnt) / blockSize));

        // 현재 블록
        setBlock((int)Math.ceil((double)(page)/blockSize));

        // 블록 시작 페이지
        setStartPage((block - 1) * blockSize + 1);

        // 블록 마지막 페이지
        setEndPage(startPage + blockSize -1 );

        // 블록 마지막 페이지는 무조건 정해져 있지 않으므로
        if(totalPageCnt < endPage) this.endPage = totalPageCnt;

        // 이전 블럭(클릭 시 이전 블럭의 마지막 페이지를 가져와야 함. ex: 11~20에서 이전 클릭 시 10을 가져와야 함)
        setPreBlock((block-1)*blockSize);

        // 1에서 이전 블록을 누르면 오류가 나므로
        if(preBlock<1) this.preBlock = 1;

        // 다음 블럭(클릭 시 다음 블럭 첫번째 페이지를 불러와야 함)
        setNextBlock((block*blockSize)+1);

        // 다음 블럭을 눌렀는데 전체 페이지 수가 끝났을 때
        if(nextBlock>totalPageCnt) this.nextBlock = totalPageCnt;

        // DB 접근 시작 INDEX
        setStartIndex((page-1) * pageSize);

    }
}
