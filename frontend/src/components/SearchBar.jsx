import styles from "../css/searchbar.module.css";

export default function SearchBar({value,results,onChange, onSelect,onClick, itemKey, itemLabel
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
              key={itemKey(item)}
              className={styles.result}
              onClick={() => onSelect(item)}
            >
              {itemLabel(item)}
            </div>
          ))}
        </div>
      )}
    </>
  );
}