import styles from '../css/searchbar.module.css';

export default function SearchBar({onChangeHandle,resultList, inputValue, setUsername, setResultList, setIsSelected}){
    console.log(resultList)
    return(
        <>
        <div className={styles.searchBarWrapper}>
            <input className={styles.searchBar}  type="text" placeholder="Type username to search" onChange={onChangeHandle} 
            value={inputValue} />
        </div>
        <div className={styles.searchResultList}>
            {resultList.map(result    =>(
                <div onClick={() =>{setUsername(result.username);setResultList([]);
                        setIsSelected(true);
                }} className={styles.result} key={result.userId}>
                    {result.username} 
                    </div>
            ))}
        </div>
        </>
    )
}