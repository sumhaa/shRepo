import React, { useEffect, useState } from "react";


//css
import "../../css/bbsList.css";
import "../../css/page.css";

//API(axios)
import axios from "axios";


//Link
import { Link } from "react-router-dom";

function BbsList(){
    const [page, setPage] = useState();
    const [pageSize, setPageSize] = useState();
    const [totalPage, setTotalPage] = useState();
    const [totalCnt, setTotalCnt] = useState();


    // 조회
    const getBbsList() = async(page) => {
        try {
            const response = await.axios.get("http://localhost:8989/board/list", {params:{"page": page -1}});

            console.log("[BbsList.js] useEffect() success :D");
            console.log(response.data);

            setBbsList(response.data.content);
            setPageSize(response.data.pageSize);
            setTotalPages(response.data.totalPages);
            setTotalCnt(response.data.totalElements);


        } catch (error) {
            console.log("[BbsList.js] useEffect() error :<");
            console.log(error);
        }

    };

    // 검색
    const search = async () => {
        try {
            const response = await.axios.get("http://localhost:8989/board/search", {params:{
                page : page -1,
                title: choiceVal === "title" ? searchVal : "" ,
                content: choiceVal === "title" ? searchVal : "",
                writername : choiceVal === "writer" ? searchVal : "",
                },
            });

            console.log("[BbsList.js] searchBtn() success :D");
            console.log(response.data);

            setBbsList(response.data.content);
            setPageSize(response.data.pageSize);
            setTotalPages(response.data.totalPages);
            setTotalCnt(response.data.totalElements);


        } catch (error) {
            console.log("[BbsList.js] searchBtn() error :<");
            console.log(error);
        }
    }

    useEffect(() => {
            getBbsList(1);
        },[]);


     const changeChoice = (event) => {
            setChoiceVal(event.target.value);
        };
        const changeSearch = (event) => {
            setSearchVal(event.target.value);
        };

    // 페이징
        const changePage = (page) => {
            setPage(page);
            getBbsList(page);
        };



    return(
        <div>
            {/* 검색 */}
            <table className="search">
                <tbody>
                    <tr>
                        <td>
                            <select className="custom-select" value={choiceVal} onChange={changeChoice}>
                                <option>검색 옵션 선택</option>
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="writer">작성자</option>
                            </select>
                        </td>

                        <td>
                            <input type="text" className="form-control" placeholder="검색어" value={searchVal} onChange={changeSearch}></input>
                        </td>

                        <td>
                            <button type="button" className="btn btn-outline-secondary" onClick={search}><i className="fas fa-search"></i> 검색</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br />

            <table className="table table-hover">
                <thead>
                    <tr>
                        <th className="col-1">번호</th>
                        <th className="col-7">제목</th>
                        <th className="col-3">작성자</th>
                        <th className="col-1">조회수</th>
                    </tr>
                </thead>

                <tbody>

                </tbody>
            </table>

            <Pagination
                className="pagination"
                activePage={page}
                itemsCountPerPage={pageSize}
                totalitemsCount={totalPages}
                prePageText={"<"}
                nextPageText={">"}
                onChange={changePage}
                />

            {/* 글쓰기 버튼 / 링크 / 아이콘 */}
            {/* /bbswrite */}
            <div className="my-5 d-flex justify-content-center">
                <Link className="btn btn-outline-secondary" to="/bbswrite"><i className="fas fa-pen"></i> &nbsp; 글쓰기</Link>
            </div>

        </div>
    );
}
export default BbsList;