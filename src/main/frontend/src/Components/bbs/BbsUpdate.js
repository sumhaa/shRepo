import React, { useContext, useEffect, useState } from "react";
import Axios from "axios";
import {useNavigate} from "react-router-dom";

import {AuthContext, AuthProvider} from "../context/AuthProvider";
import {HttpHeadersContext, HttpHeadersProvider} from "../context/HttpHeadersProvider";

import "../../css/bbsupdate.css";

function BbsUpdate() {
   // 인증 (로그인) AuthProvider, HttpHeaderProvider
        const [auth, setAuth] = useContext(AuthContext);
        const [headers, setHeaders] = useContext(HttpHeadersContext);

        const navigate = useNavigate();

        // 주소 설정
        const location = useLocation();
        const {bbs} = location.state;

        // 게시판
        const boardId = bbs.boardId;

        // 가져오기
        const [title, setTitle] = useState(bbs.title); // 제목
        const [content, setContent] = useState(bbs.content); // 내용
        const [files, setFiles] = useState([]); // 파일
        const [serverFiles, setServerFiles] = useState(bbs.files || []); // 첨부 (저장되어있는 파일)

          // changeTitle
        const changeTitle = (event) => {
            setTitle(event.target.value);
        };

        // changeContent
        const changeContent = (event) => {
            setContent(event.target.value);
        };

        //file
        const handleChangeFile = (event) => {

            const selectedFiles = Array.from(event.target.files).slice(0,5);
            setFiles((prevFiles) => [...prevFiles, ...selectedFiles]);
        };

        // filter
        const handleRemoveFile = (index) => {
            setFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
        };

        const handleRemoveServerFile = (index, boardId, fileId) => {
            setServerFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
            fileDelete(boardId, fileId);
        };

        // 토큰
        useEffect(() => {
            setHeaders({
                Authorization: `Bearer ${localStorage.getItem("bbs_access_token")}`,
            });
        },[]);


   // 파일 업로드 (fileUpload) - post
    const fileUpload = async () => {
        await axios.post(`http://localhost:8989/board/{boardId}/file/upload`)
                .then((resp) => {
                console.log("[BbsUpdate.js] fileUpload() success");
                console.log(resp.data);

                }
    }).catch (error) {
        console.log("[BbsUpdate.js] fileUpload() error");
        console.log(error);
    }

    // 파일 삭제 (fileDelete) - delete
    const fileDelete = async (boardId, fileId) => {
        try {
            await axios.delete(`http://localhost:8989/board/{boardId}/file/delete`);
            console.log("[BbsUpdate.js] fileDelete() success");
        } catch (error) {
            console.log("[BbsUpdate.js] fileDelete() error");
            console.log(error);
        }
    }

   // 게시판 수정 (updateBbs) - put?
   const updateBbs = async () => {
               const req = {
                   id: auth,
                   title: title,
                   content: content
               }

               await axios
               .patch(`http://localhost:8989/board/${bbs.boardId}/file/update`, req, {headers: headers})
               .then((resp) => {
                   console.log("[BbsUpdate.js] updateBbs() success :D");
                   console.log(resp.data);

                   const boardId = resp.data.boardId;

                   if (files.length > 0) {
                       fileUpload(boardId);
                   } else {
                       alert("게시글을 성공적으로 수정했습니다.")
                       navigate(`/bbsdetail/${resp.data.boardId}`);
                   }
               })
               .catch((err) => {
                   console.log("[BbsUpdate.js] updateBbs() error :<");
                   console.log(err);
               });
           }


}
export default BbsUpdate;