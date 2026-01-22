import { useState,useEffect } from 'react';
import { Col,Container,Row,Button,Modal,Form, ModalBody,Badge } from 'react-bootstrap';
import SearchBar from '../components/SearchBar';
import axios from 'axios';
import { data } from 'react-router-dom';
import GroupCardComponent from '../components/GroupCardComponent';


export default function GroupPage(){

    const [showCreateGroup,setShowCreateGroup] = useState(false);
    const [groupName,setGroupName] = useState("");
    const [groupPurpose,setGroupPurpose] = useState("");
    const [groupMember,setGroupMember] = useState([]);
    const [groupMemberId,setGroupMemberId] = useState([]);
    const [friendList,setFriendList] = useState([]);
    const [searchForFriend,setSearchForFriend] = useState("");
    const [startSearch, setStartSearch] = useState(false);
    const [groups,setGroups] =useState([]);

    const token = localStorage.getItem("token")

     const getFriendList = async () => {
        try {
            const response = await axios.get("http://localhost:8080/friends/search",{
                params :{
                  page : 0,
                  size : 5,
                  search : searchForFriend
                },
                headers:{
                      "Authorization": `Bearer ${token}`
                }
            })
            setFriendList(response.data.content)
            console.log(response.data)
        } catch (error) {
            console.log(error)
        }
    }
     const getGroupList = async () => {
        try {
            const response = await axios.get("http://localhost:8080/groups",{
                 headers:{
                      "Authorization": `Bearer ${token}`
                }
            }
               
               
            )
            setGroups(response.data)
            console.log(response.data)
        } catch (error) {
            console.log(error)
        }
    }

     useEffect(() => {
        if (token && startSearch) {
            getFriendList();
        }
    }, [token, searchForFriend]);

    useEffect(() => {
        if(token){
            getGroupList();
        }
        
                },[token])


    const createGroup = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(
                "http://localhost:8080/groups",
                {
                    groupName: groupName,
                    listOfAddingMember: groupMemberId,
                    groupPurpose: groupPurpose
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            getGroupList();
        } catch (error) {
            console.log(error);
        }
        };

    return (
        <>
        <header>
          <Container>
                <Row>
                    <Col className="d-flex align-items-center justify-content-between">
                        <h5 className="fw-bold">Your Groups</h5>
                        <div className="">
                            
                            <Button size="sm" className="fw-bold rounded py-1 px-3"
                        onClick={() => setShowCreateGroup(true)}
                        >Create Group</Button>
                        </div>
                        
                    </Col>
                </Row>
            </Container>

              <Modal        
                   show={showCreateGroup}
                            size="lg"
                            aria-labelledby="contained-modal-title-vcenter"
                            centered
                            onHide={() => setShowCreateGroup(false)}
                        >
                            <Modal.Header closeButton>
                                <Modal.Title id="contained-modal-title-vcenter">
                               Create Group     
                                </Modal.Title>
                            </Modal.Header>    
                            <ModalBody>
                              <Form onSubmit={createGroup}>
                                <Form.Group>
                                <Form.Label>Group Name</Form.Label>
                                <Form.Control type='text' placeholder='Enter your group name' onChange={(e) => setGroupName(e.target.value)}></Form.Control>
                                </Form.Group>
                                <Form.Group className='mt-2'>
                                <Form.Label>Group Purpose</Form.Label>
                                <Form.Select onChange={(e) => setGroupPurpose(e.target.value)}>
                                    <option value={null}>Open this select menu</option>
                                    <option value="TRIP">TRIP</option>
                                    <option value="PARTY">PARTY</option>
                                    <option value="SUBCRIBTION">SUBCRIBTION</option>
                                    <option value="SHARED_LIVING">SHARE LIVING</option>
                                    <option value="OTHER">Other</option>     
                                    </Form.Select>
                                    </Form.Group>
                                <Form.Group className='mt-2'>
                                <Form.Label>Group Member</Form.Label>
                                <SearchBar
                                        value={searchForFriend}
                                        results={friendList}
                                        placeholder="Add group member"
                                        onChange={(value) => setSearchForFriend(value)}
                                        onClick={() => setStartSearch(true)}
                                        onSelect={(user) => {
                                            setGroupMember(prev => [...prev, user]);
                                            setGroupMemberId(prev => [...prev,user.userId])
                                            setSearchForFriend("");
                                            setFriendList([]);
                                            setStartSearch(false);
                                        }}
                                        />
                                  <div className="d-flex flex-wrap gap-2 mt-2">
                                    {groupMember.map(member => (
                                        <div
                                        key={member.userId}
                                        className="d-flex align-items-center bg-light border rounded-pill px-2 py-1"
                                        style={{ fontSize: "0.85rem" }}
                                        >
                                        <span className="me-2">{member.username}</span>

                                        <button
                                            type="button"
                                            className="btn-close btn-close-sm"
                                            onClick={() =>{
                                            setGroupMember(prev =>
                                                prev.filter(m => m.userId !== member.userId)
                                            );
                                            setGroupMemberId(prev =>
                                                prev.filter(m => m !== member.userId)
                                            );
                                        }
                                            }
                                        />
                                        </div>
                                    ))}
                                    </div>
                            
                                </Form.Group>
                                <div className="d-flex gap-2 mt-3">
                                <Button variant="secondary" onClick={() => setShowCreateGroup(false)} className="flex-fill">Cancel</Button>
                                <Button  className="flex-fill" type="submit" onClick={() => setShowCreateGroup(false)} >Add</Button>
                                </div>
                              </Form>
                            </ModalBody>
                        </Modal>
                        </header>
                        <section>

                                <Container>
                                    <Row>
                                        {groups.map((group) => (
                                            <Col xs={3} key={group.groupId} >
                                                <GroupCardComponent  title={group.groupName} memberList={group.memberName}/>
                                            </Col>
                                        ))}
                                    </Row>
                                </Container>
                        </section>
            
        </>
    )
}