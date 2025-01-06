import React, { useContext, useEffect, useState } from "react";
import Axios from "axios";
import {useNavigate} from "react-router-dom";

import {AuthContext, AuthProvider} from "../context/AuthProvider";
import {HttpHeadersContext, HttpHeadersProvider} from "../context/HttpHeadersProvider";

import "../../css/bbswrite.css";

function BbsWrite () {

    const {auth, setAuth} = useContext();
    const {headers, setHeaders} = useContext();

    const navigate = useNavigate();

    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [files, setFiles] = useState([]);

    const changeTitle = (event) => {
        setTitle(event.target.value);
    };

    const changeContent = (event) => {
        setContent(event.target.value);
    };

     //file
     const handleChangeFile = (event) => {
            // 총 5개까지만 허용
          const selectedFiles = Array.from(event.target.files).slice(0,5);
          setFiles((prevFiles) => [...prevFiles, ...selectedFiles]);
     };

        // filter() - 주어진 배열의 일부에 대한 얕은 복사본을 생성
     const handleRemoveFile = (index) => {
          setFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
     };

    // REST API

    const fileUpload = async (boardId) => {
        console.log("업로드 할 파일 목록: ", files);

        const fd = new FormData();
        files.forEach((file) => fd.append("file", file));

        await axios.post(`http://localhost:8989/board/{boardId}/file/upload`, fd, {headers: headers})
                .then((resp) => {
                    console.log("[file.js] fileUpload() success :D");
                    console.log(resp.data);
                    alert("파일 업로드 성공");
                })
                .catch((err) => {
                    console.log("[fileData.js] fileUpload() error :<");
                    console.log(err);
                });
    };







    return (
        <div>
                <table className="table">
                    <tbody>
                        <tr>
                            <th className="table-primary">작성자</th>
                            <td>
                                <input type="text" className="form-control" value={localStorage.getItem("id")} size="50px" readOnly></input>
                            </td>
                            <th className="table-primary">제목</th>
                            <td>
                                <input type="text" className="form-control" value={title} size="50px" onChange={changeTitle}></input>
                            </td>
                            <th className="table-primary">내용</th>
                            <td>
                                <textarea className="form-control" value={content} onChange={changeContent} rows="10"></textarea>
                            </td>
                        </tr>

                        <tr>
                            <th className="table-primary">파일</th>
                            <td>
                                {files.map((file,index) => (
                                    <div key={index} style={{display: "flex", alignItems: "center"}}>
                                        <p>
                                            <strong>FileName:</strong> {file.name}
                                        </p>
                                        <button className="delete-button" type="button" onClick={() => handleRemoveFile(index)}>X</button>
                                    </div>
                                ))}
                                {files.length < 5 && (
                                    <div>
                                        <input type="file" name="file" onChange={handleChangeFile} multiple="multiple"></input>
                                    </div>
                                )}
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div className="my-5 d-flex justify-content-center">
                    <button className="btn btn-outline-secondary" onClick={createBbs}>
                    <i className="fas fa-pen"></i> 등록하기
                    </button>
                </div>

            </div>
        );
    );
}