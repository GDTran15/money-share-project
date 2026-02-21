import { useState,useEffect } from 'react';
import { Col,Container,Row,Button,Nav } from 'react-bootstrap';
import SearchBar from '../components/SearchBar';
import axios from 'axios';
import style from '../css/navnar.module.css';
import OweShareRequestPage from '../components/oweShareRequestPage';
import SharingRequestPage from '../components/SharingRequestPage';



export default function ShareRequestPage(){ 

    const [currentTab,setCurrentTab] = useState("owe")

    let mainContent;

    switch(currentTab){
            case "owe":
                mainContent = <OweShareRequestPage/>
            break;
            case "sharing":
                mainContent = <SharingRequestPage/>
            break;
            
    
        }
    
        

        return(<>
                <header>
                        <Container>
                                <Row>
                                    <Col className="d-flex align-items-center justify-content-between">
                                        <h5 className="fw-bold">Share Request</h5>
                                        <div className="">
                                            
                                        <div>
                                            <Nav>
                                                <Nav.Item>
                                                    <Nav.Link className={currentTab == "owe" ? style.navtabActive : "text-secondary fw-semibold"}
                                                                        onClick={() => setCurrentTab("owe")}>
                                                        Your Owe
                                                    </Nav.Link>
                                                </Nav.Item>
                                                <Nav.Item>
                                                    <Nav.Link className={currentTab == "sharing" ? style.navtabActive : "text-secondary fw-semibold"}
                                                                        onClick={() => setCurrentTab("sharing")}>
                                                        Sharing With You
                                                    </Nav.Link>
                                                </Nav.Item>
                                            </Nav>
                                        </div>
                                        </div>
                                        
                                    </Col>
                                </Row>
                            </Container>
                        </header>
                        <section>
                            {mainContent}
                        </section>
                        
         </>)
}