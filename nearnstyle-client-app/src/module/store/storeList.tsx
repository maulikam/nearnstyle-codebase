import React from 'react';
import {
  SafeAreaView,
  FlatList,
  StyleSheet,
  StatusBar
} from 'react-native';
import { StoreItem } from './storeItem';
import { Store } from './store';
import { lightColors } from '@rneui/base';

export const StoreList: React.FC = () => {
  const data: Store[] = [
    {
      id: 1,
      name: "Store 1",
      address: "234, cross road, mumbai, 200202",
      imageUrl: "https://www.adobe.com/content/dam/cc/us/en/creativecloud/design/discover/mascot-logo-design/mascot-logo-design_fb-img_1200x800.jpg",
    },
    {
      id: 2,
      name: "Store 2",
      address: "390, sector 8, ahmedabad, 380202",
      imageUrl: "https://thumbs.dreamstime.com/b/b-letter-boutique-logo-design-159417325.jpg"
    },
    {
      id: 3,
      name: "Store 3",
      address: "12/5, cambrige area, banglore, 400202",
      imageUrl: "https://t3.ftcdn.net/jpg/04/79/35/56/360_F_479355620_S9342mf8ufDwjYSmLy8LRBPDZswkMN2L.jpg"
    },
    {
      id: 4,
      name: "Store 4",
      address: "301, near univercity, surat, 360202",
      imageUrl: "https://marketplace.canva.com/EAFaFAYoqtY/1/0/400w/canva-gold-luxury-business-logo-kc3wKtJf09k.jpg"
    }
  ];

  return (
    <SafeAreaView style={styles.container}>
      <FlatList
        data={data}
        renderItem={({ item }) => <StoreItem {...item} />}
        keyExtractor={item => item.id.toString()}
      />
    </SafeAreaView>
  )
};
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: lightColors.background,
    marginTop: StatusBar.currentHeight || 0,
  },
});