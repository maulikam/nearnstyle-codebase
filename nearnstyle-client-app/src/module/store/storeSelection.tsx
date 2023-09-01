import React from "react";
import { StyleSheet, View } from 'react-native';
import { lightColors } from '@rneui/themed';
import { StoreSearch } from "./storeSearch";
import { StoreList } from "./storeList";

export const StoreSelection: React.FC = () => {

    const storeSearchValueChange = (searchValue: string) => {
        console.log(searchValue)
    }

    return (
        <View style={styles.container}>
            <StoreSearch
                searchValueChange={storeSearchValueChange} />
            <StoreList />
        </View>
    );
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