import styles from "../css/searchbar.module.css";

export default function SearchBar({value,results,onChange, onSelect,onClick
}) {
  return (
    <>
      <div className={styles.searchBarWrapper}>
        <input
          className={styles.searchBar}
          type="text"
          onClick={onClick}
          value={value}
          onChange={(e) => onChange(e.target.value)}
        />
      </div>

      {results.length > 0 && (
        <div className={styles.searchResultList}>
          {results.map((item) => (
            <div
              key={item.userId}
              className={styles.result}
              onClick={() => onSelect(item)}
            >
              {item.username}
            </div>
          ))}
        </div>
      )}
    </>
  );
}