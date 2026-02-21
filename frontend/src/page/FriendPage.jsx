    import { useEffect, useState } from "react"
    import { Col,Container,Row,Button,Modal,Form, ModalBody,Badge } from 'react-bootstrap';
    import Select from "react-select/base";
    import SearchBar from "../components/SearchBar";
    import axios from "axios";
import { data } from "react-router-dom";
import FriendRequestComponent from "../components/FriendRequestComponent";
import FriendList from "../components/FriendList";


    export default function FriendPage(){
        const [showAddFriend,setShowAddFriend] = useState(false);
        const [addFriendError,setAddFriendError] = useState(""); //need add nin the future
        const [showRequest,setShowRequest] = useState(false);
        const [username,setUsername] = useState("");
        const [error,setError] = useState("");
        const [result,setResult] = useState([]);
        const token = localStorage.getItem("token");
        const [isSelected, setIsSelected] = useState(false);
        const [inComingRequest,setIncomingRequest] = useState([]);
        const [numberOfInComeRequest,setNumberOfInComeRequest] = useState(0);
        
    const addFriend = async (e) => {
            e.preventDefault();
            setAddFriendError("");
            try {
                const response = await axios.post("http://localhost:8080/friend-request", null, {
                    params: {username},
                     headers:{
                "Authorization": `Bearer ${token}`
            }
                });
               
            } catch (error) {
                
                alert(error.response.data);
            }
        }
    



    const fetchIncomingFriendRequest = async () => {
        try {
            const response = await axios.get("http://localhost:8080/friend-request/receiver", {
                headers:{
                "Authorization": `Bearer ${token}`
            }
        });
            console.log(response)
        } catch (error){
            console.log(error)
        }
    } 
   
        
    useEffect(() => {
         const fetchNumberOfIncomingFriendRequest = async () => {
        try {
            const response = await axios.get("http://localhost:8080/friend-request/receiver/count", {
                headers:{
                "Authorization": `Bearer ${token}`
            }
        });
        console.log(data)
            setNumberOfInComeRequest(response.data)
        } catch (error){
            console.log(error)
        }
    } 
     if (token) {
        fetchNumberOfIncomingFriendRequest();
    }
    }, [token])


    const fetchUsers = async () => {
    try {
        const response = await axios.get("http://localhost:8080/users", {
        params: {
            page: 0,
            size: 5,
            search: username
        },
        headers:{
                "Authorization": `Bearer ${token}`
            }
        });
        console.log(response)
        setResult(response.data.content);
    } catch (error) {
        console.error(error);
    }
    };

        useEffect(() => {           
            if(isSelected){
                setIsSelected(false);
                return;
            }
            if (!username.trim()) {
                    setResult([]);
                return;
                }
            const timer = setTimeout(fetchUsers,500);
            return () => clearTimeout(timer);
        }
            , [username]);

        return (
            <>
        <header>
            <Container>
                <Row>
                    <Col className="d-flex align-items-center justify-content-between">
                        <h5 className="fw-bold">Your Friends</h5>
                        <div className="d-flex gap-2">
                            <Button size="sm" className="fw-bold rounded py-1 px-3 d-flex gap-1 " onClick={() => setShowRequest(true)}>
                                Request
                                <Badge pill bg="danger" className="align-content-center">{numberOfInComeRequest}</Badge>
                            </Button>
                            <Button size="sm" className="fw-bold rounded py-1 px-3"
                        onClick={() => setShowAddFriend(true)}
                        >Add Friend</Button>
                        </div>
                        
                    </Col>
                </Row>
            </Container>
             <Modal        
                show={showRequest}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
                onHide={() => setShowRequest(false)}
            >
                <Modal.Header closeButton className="border-0 pb-0">
                    <Modal.Title id="contained-modal-title-vcenter">
                   Friend Requests     
                    </Modal.Title>
                </Modal.Header>    
                <ModalBody>
                    <FriendRequestComponent onHandle={() => setNumberOfInComeRequest(prev => prev - 1)}/>
                </ModalBody>
            </Modal>

            {/* Add Friend */}
            <Modal        
                show={showAddFriend}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
                onHide={() => setShowAddFriend(false)}
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                    Add a Friend       
                    </Modal.Title>
                </Modal.Header>    
                <ModalBody>
                    <Form onSubmit={addFriend}>
                        <Form.Group className="mb-3">
                            <Form.Label>Username</Form.Label>
                            <SearchBar
                              value={username}
                              itemKey={(item) => item.userId}
                              itemLabel={(item) => item.username}
                                results={result}
                                placeholder="Search username"
                                onChange={(value) => setUsername(value)}
                                onSelect={(user) => {
                                    setUsername(user.username);
                                    setResult([]);
                                }}/>
                        </Form.Group>
                        
                        <div className="d-flex gap-2">
                            <Button variant="secondary" onClick={() => setShowAddFriend(false)} className="flex-fill">Cancel</Button>
                            <Button  className="flex-fill" type="submit" onClick={() => setShowAddFriend(false)} >Add</Button>
                        </div>
                    </Form>
                    {error === "" ? "" : <p className="text-danger">*{error}</p>}
                </ModalBody>
            </Modal>
            </header>
            <section>
                <FriendList/>
            </section>
            </>
        )
    }