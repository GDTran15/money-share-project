import axios from "axios";
import { useState } from "react"
import InputComponent from "../components/InputComponent";
import FormComponent from "../components/FormComponent";
import { useNavigate } from "react-router-dom";

export default function LoginPage(){
    const navigate = useNavigate();
    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");
    const [error,setError] = useState("");
    const [validError,setValidError] = useState({});
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setValidError({});
        try {
            const res  = await axios.post("http://localhost:8080/login",{

                username: username,
                password: password

            });
            setUsername(""); setPassword("");
            console.log(res)
            localStorage.setItem("token",res.data.token);
            localStorage.setItem("username",res.data.username)
            navigate("/home");
        } catch (error){
            console.log(error)
             if (typeof error.response.data === "string") {
      
        setError(error.response.data);
    }   else {
        setValidError(error.response.data);
          
        
    }
    } 

    }

    return(
        <>
             <FormComponent title="Login" subTitle="Join splitter community"
                optional="Register"
                linkTo="/register">
                 <form onSubmit={handleSubmit}>
                 <InputComponent
                 labelText="Username"
                 inputType="text"
                 changeHandle={(e) => setUsername(e.target.value)}
                 inputValue={username}
                 placeholderValue="trangiaiduong"
                 validationError={validError.username}
                 >
                  </InputComponent>
                 <InputComponent
                 labelText="Password"
                 inputType="password"
                 inputValue={password}
                 changeHandle={(e) => setPassword(e.target.value)}
                 validationError={validError.password}
         
                 >
                  </InputComponent>
                

                              <button type="submit" className="btn btn-warning w-100 mt-2">Login</button>
                  <p className="text-danger mt-3">{error !== "" ?  `*${error}` : ""}</p>
                 </form>
                </FormComponent>
        </>
    )
}