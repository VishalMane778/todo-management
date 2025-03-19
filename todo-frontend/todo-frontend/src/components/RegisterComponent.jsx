import { useState } from "react"

const RegisterComponent=()=>
{
    const [name,setName]=useState('');
    const [username,setUsername]=useState('')
    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')
    return (
         <div className="container"><br/><br/>
            <div className="row">
                <div className="col-md-6 offset-md-3">
                    <div className="card">
                        <div className="card-header">
                            <h2 className="text-center">User Registration Form</h2>
                        </div>
                        <div className="card-body">
                            <form>
                                <div className="row mb-3">
                                    <label className="col md-3 control-label">Name : </label>
                                    <div className="col md-9">
                                        <input
                                        type="text"
                                        placeholder="Enter Name"
                                        value={name}
                                        name="name"
                                        className="form-control"
                                        onChange={(e)=>setName(e.target.value)}
                                        ></input>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label className="col md-3 control-label">Username : </label>
                                    <div className="col md-9">
                                        <input
                                        type="text"
                                        placeholder="Enter Username"
                                        value={username}
                                        name="username"
                                        className="form-control"
                                        onChange={(e)=>setUsername(e.target.value)}
                                        ></input>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label className="col md-3 control-label">Email : </label>
                                    <div className="col md-9">
                                        <input
                                        type="text"
                                        placeholder="Enter Email"
                                        value={email}
                                        name="email"
                                        className="form-control"
                                        onChange={(e)=>setEmail(e.target.value)}
                                        ></input>
                                    </div>
                                </div>
                                <div className="row mb-3">
                                    <label className="col md-3 control-label">Password : </label>
                                    <div className="col md-9">
                                        <input
                                        type="password"
                                        placeholder="Enter Password"
                                        value={password}
                                        name="password"
                                        className="form-control"
                                        onChange={(e)=>setPassword(e.target.value)}
                                        ></input>
                                    </div>
                                </div>
                                <div className="form-group mb-3">
                                     <button className="btn btn-primary" onClick={(e)=>handleRegistrationForm(e)}> Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
         </div>
    )
}
export default RegisterComponent