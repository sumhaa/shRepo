import React, { useContext } from "react";
import {AuthContext} from "../context/AuthProvider";
import { Link } from "react-router-dom";



function Header(){
    //변수
    const {auth, setAuth} = useContext(AuthContext);
    return(
        <Header>
            <nav className="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
                <div className="container">
                    <div className="navbar-collapse collapse justify-content-between" id="navbar-content">

                        <ul className="navbar-nav mr-auto">
                            {/* 메인화면 */}
                            <li className="nav-item">
                                <Link className="nav-link" to="/">
                                {/* 아이콘 */}
                                <i className="fas fa-home"></i> Home
                                </Link>
                            </li>
                            {/* 게시판 */}
                            <li className="nav-item dropdown">
                                <div
                                className="nav-link dropdown-toggle"
                                id="navbarDropdown"
                                role="button"
                                data-toggle="dropdown"
                                aria-haspopup="true" // 드롭다운 (하위메뉴) 팝업 관련 (웹 접근성)
                                aria-expanded="false" // 드롭다운 (하위메뉴) 팝업 관련 (웹 접근성)
                                >
                                게시판
                                </div>

                                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <Link className="dropdown-item" to="/">
                                        글목록
                                    </Link>
                                    <Link className="dropdown-item" to="/">
                                        글추가
                                    </Link>
                                </div>
                            </li>
                        </ul>

                        <ul className="navbar-nav ml-auto">
                        {auth ? (
                        <>
                            {/* 회원정보 */}
                            <li className="nav-item">
                                <Link className="nav-link" to="/checkpwd">
                                    {/* 아이콘 : 폰트어썸 */}
                                    <i className="fas fa-sign-out-alt"></i> {auth} 님 반갑습니다. <i className="fab fa-ello"></i>
                                    {" "}&nbps;{" "}
                                </Link>
                            </li>

                            {/* 로그아웃 */}
                            <li className="nav-item">
                                <Link className="nav-link" to="/logout">
                                    <i className="fas fa-sign-out-alt"></i> 로그아웃
                                </Link>
                            </li>
                        </>
                        ) : (
                        <>
                            {/* 로그인 */}
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">
                                    로그인
                                </Link>
                            </li>

                            {/* 회원가입 */}
                            <li className="nav-item">
                                <Link className="nav-link" to="/join">
                                    회원가입
                                </Link>
                            </li>
                        </>
                        )}
                        </ul>
                    </div>
                </div>
            </nav>
        </Header>
    );
}

export default Header;