import React, { useEffect, useState } from "react";
// import { debounce } from "lodash";
import { StyleSheet, View } from 'react-native';
import { SearchBar, lightColors } from '@rneui/themed';

interface StoreSearchProps {
    searchValueChange: (searchValue: string) => void
}

export const StoreSearch: React.FC<StoreSearchProps> = (props: StoreSearchProps) => {
    console.log(props)
    const [searchText, setSearchText] = useState('');
    const searchTextChange = (searchValue: string) => {
        // props.searchValueChange(searchValue);
    };

    useEffect(() => {
        searchTextChange(searchText)
    }, [searchText])

    return (
        <SearchBar
            containerStyle={styles.searchBar}
            inputContainerStyle={styles.searchBarInput}
            lightTheme={true}
            showCancel={true}
            round={true}
            placeholder="Search area, city, pincode"
            onChangeText={setSearchText}
            value={searchText}
        />
    )
};
const styles = StyleSheet.create({
    container: {
        flex: 1
    },
    searchBar: {
        backgroundColor: '#FFFFFF'
    },
    searchBarInput: {
        backgroundColor: lightColors.grey5
    }
});