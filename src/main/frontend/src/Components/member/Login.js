/* 로그인 컴포넌트 */

//기본
import axios from "axios";
import { useState , useContext} from "react";
import { useNavigate } from "react-router";

//인증
import { AuthContext } from "../context/AuthProvider";
import { HttpHeadersContext } from "../context/HttpHeadersProvider";


//---------------
function Login(){
    //설정
    const [auth, setAuth] = useContext(AuthContext);
    const [headers, setHeaders] = useContext(HttpHeadersContext);

    //이동
    const useNavigate = useNavigate();

    //이벤트
    const [id,setId] = useState("");
    const [pwd, setPwd] = useState("");

    const ChangeId = (event) => {
        setId(event.target.value);
    }
    const ChangePwd = (event) => {
        setPwd(event.target.value);
    }


    //REST API (로그인)


    //HTML
    return (
        <div>
            <table className="table">
                <tbody>
                <tr>
                    <th className="col-3">아이디</th>
                    <td>
                        <input type="text" value={id} onChange={changeId} size="50px" />
                    </td>
                </tr>

                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input
                            type="password"
                            value={pwd}
                            onChange={changePwd}
                            size="50px"
                        />
                    </td>
                </tr>
                </tbody>
            </table>
            <br />

            <div className="my-1 d-flex justify-content-center">
                <button className="btn btn-outline-secondary" onClick={login}>
                    <i className="fas fa-sign-in-alt"></i> 로그인
                </button>
            </div>
        </div>
    );
}
export default Login;

