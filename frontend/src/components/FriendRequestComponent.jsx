import { useCallback, useEffect, useState } from "react"
import style from '../css/navnar.module.css';
import { Row ,Col,Container,Nav } from 'react-bootstrap';
import axios from "axios";
import RequestComponent from "./RequestComponent";


export default function FriendRequestComponent({onHandle}){
    const [currentTab,setCurrentTab] = useState("received");
    const [request,setRequest] = useState([]);
    const [sent,setSent] = useState([]);
    const [requestFetch,setRequestFetch] = useState(false);
    const [sentFetch,setSentFetch] = useState(false);
     const token = localStorage.getItem("token");
    
     const fetchSentFriendRequest  = useCallback(async () => {
        try {
            const response = await axios.get("http://localhost:8080/friend-request/sent", {
                headers:{
                "Authorization": `Bearer ${token}`
            }
        });
            console.log(response.data);
            setSent(response.data);
            setSentFetch(true);
        } catch (error){
            console.log(error)
        }
    },[])


     const fetchIncomingFriendRequest = useCallback(async () => {
        try {
            const response = await axios.get("http://localhost:8080/friend-request/receiver", {
                headers:{
                "Authorization": `Bearer ${token}`
            }
        });
            console.log(response.data);
            setRequest(response.data);
            setRequestFetch(true);
        } catch (error){
            console.log(error)
        }
    },[])

    useEffect(() => {
        if(currentTab === "received" && !requestFetch){
        fetchIncomingFriendRequest();
        setRequestFetch(true);
        }
    })
    
    return (<>
        <div className="border-bottom border-secondary-subtle">
            <Container>
                <Nav
                activeKey="/home"
                onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}
                
                >
                <Nav.Item> 
                    <Nav.Link className={currentTab == "received" ? style.navtabActive : "text-secondary fw-semibold"} 
                    onClick={() => setCurrentTab("received")}
                    >Received</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link className={currentTab == "sent" ? style.navtabActive : "text-secondary fw-semibold"}
                    onClick={() => { setCurrentTab("sent"),fetchSentFriendRequest();}}
                    >Sent</Nav.Link>
                </Nav.Item>
            </Nav>
            </Container>
            </div>
            <Container>
                <Row>
                    {currentTab === "received" ? (request.map((req) => (
                        <RequestComponent username={req.receiverName} key={req.friendRequestId} friendRequestId={req.friendRequestId}
                        handleUserResponse={() => {setRequest(prev => prev.filter(r => r.friendRequestId !== req.friendRequestId));
                            onHandle();
                        }}
                        type={"received"}
                        />
                    ))) : (sent.map((s) => (
                        <RequestComponent username={s.requesterName} key={s.friendRequestId} friendRequestId={s.friendRequestId} type={"sent"}/>
                    )))}
                    
                </Row>
            </Container>
        
        
    
    </>)
}