import { View, ActivityIndicator, StyleSheet } from 'react-native';
import { Card, Button, Text, Icon } from '@rneui/themed';
import { Store } from './store';

export const StoreItem: React.FC<Store> = (item: Store) => {
    return (
        <Card wrapperStyle={styles.mainContainer}>
            <Card.Image
                containerStyle={styles.image}
                source={{
                    uri: item.imageUrl,
                }}
                PlaceholderContent={<ActivityIndicator />}
            />
            <View style={styles.detailsContainer}>
                <Text style={styles.name}>{item.name}</Text>
                <View style={styles.addressContainer}>
                    <Icon name='location' />
                    <Text numberOfLines={1} style={styles.address}>{item.address}</Text>
                </View>
                <Button buttonStyle={styles.bookBtnInner} containerStyle={styles.bookBtn}>Book</Button>
            </View>
        </Card >
    )
}

const styles = StyleSheet.create({
    mainContainer: {
        flex: 1,
        flexDirection: 'row',
    },
    detailsContainer: {
        paddingStart: 8,
        paddingEnd: 8,
        flex: 3
    },
    name: {
        fontSize: 18,
        fontWeight: '700'
    },
    addressContainer: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center'
    },
    address: {
        fontSize: 16,
        marginStart: 4
    },
    image: {
        width: 100,
        height: 86,
    },
    bookBtn: {
        width: 200,
        marginTop: 12
    },
    bookBtnInner: {
        borderRadius: 8,
    },
});