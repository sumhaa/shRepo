import React from "react";
import {Link} from "react-router-dom";

function Home(){

    return (
        <div className="container mt-5">
            <div className="jumbotron">
                <h1 className="display-4">Welcome to My Board !</h1>
                <p className="lead">community...</p>
                <hr className="my-4" />
                <p>Ready to get started ?</p>
                <Link to="/bbslist">
                    <button className="btn btn-primary btn-lg">Go To Board List</button>
                </Link>
                <br />

                <div className="mt-4">
                    <h3> Source code on GitHub:</h3>
                    <ul>
                        <li>
                            <a href="https://github.com/sumhaa" target="_blank" rel="">Repository</a>
                        </li>
                    </ul>
                </div>

                <div className="mt-4">
                    <h5> Contact me email</h5>
                    - &nbsp;
                    <a href="mailto:rallarura92@naver.com">rallarura92@naver.com</a>
                </div>
            </div>
        </div>
    );

}
export default Home;