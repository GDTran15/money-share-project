import { useState } from "react"
import DashboardPage from "./DashboardPage";
import FriendPage from "./FriendPage";
import GroupPage from "./GroupPage";
import style from '../css/navnar.module.css';
import { Nav,Container,Row,Button } from 'react-bootstrap';
import ExpensePage from "./ExpensePage";
import ShareRequestPage from "./ShareRequestPage";

export default function HomePage(){
    const [currentTab,setCurrentTab] = useState("dashboard");
    const username = localStorage.getItem("username");
    let mainContent;

    switch(currentTab){
        case "dashboard":
            mainContent = <DashboardPage/>
        break;
        case "friend":
            mainContent = <FriendPage/>
        break;
        case "group":
            mainContent = <GroupPage/>
        break;
        case "expense" :
            mainContent = <ExpensePage/>
        break;
        case "share-request":
            mainContent = <ShareRequestPage/>
        break;

    }
    return(
        <>
       
            <div className='bg-white py-3 ' >
                 <Container>
                <Row >
                    <div className=' d-flex align-content-center justify-content-between'>
               
                    <h3>Split That Thing</h3> 
                    <div className='d-flex align-items-center gap-2'>
                        <p className='mb-0'>Welcome, {username}</p>
                     <Button size='sm' >Logout</Button>
                    </div>  
                    

                    </div>
                </Row>
            </Container>
            </div>
            <div className="border-bottom border-secondary-subtle">
            <Container>
                <Nav
                activeKey="/home"
                onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}
                
                >
                <Nav.Item> 
                    <Nav.Link className={currentTab == "dashboard" ? style.navtabActive : "text-secondary fw-semibold"} 
                    onClick={() => setCurrentTab("dashboard")}
                    >DashBoard</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link className={currentTab == "group" ? style.navtabActive : "text-secondary fw-semibold"}
                    onClick={() => setCurrentTab("group")}
                    >Groups</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link className={currentTab == "friend" ? style.navtabActive : "text-secondary fw-semibold"}
                    onClick={() => setCurrentTab("friend")}
                    >Friends</Nav.Link>

                </Nav.Item>
                <Nav.Item>
                    <Nav.Link className={currentTab == "expense" ? style.navtabActive : "text-secondary fw-semibold"}
                    onClick={() => setCurrentTab("expense")}
                    >
                    Expense
                    </Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link className={currentTab == "share-request" ? style.navtabActive : "text-secondary fw-semibold"}
                    onClick={() => setCurrentTab("share-request")}
                    >
                    Share-Request
                    </Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link className={currentTab == "payment" ? style.navtabActive : "text-secondary fw-semibold"}
                    onClick={() => setCurrentTab("payment")}
                    >
                    Payment
                    </Nav.Link>
                </Nav.Item>
            </Nav>
            </Container>
        </div>
          <section className='mt-4'>
            {
                mainContent
            }
        </section>
        </>
    )
}