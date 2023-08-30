import React from 'react';

import { Platform } from 'react-native';
import { lightColors, createTheme, ThemeProvider } from '@rneui/themed';
import { StoreList } from './src/module/store/storeList';

const theme = createTheme({
  lightColors: {
    ...Platform.select({
      default: lightColors.platform.android,
      ios: lightColors.platform.ios,
    }),
    primary:'#512DA8',
    secondary:'#FF5722',
    
  },
  mode:'light',
});
function App(): JSX.Element {
  return (
    <ThemeProvider theme={theme}>
      <StoreList />
    </ThemeProvider>
  );
}

export default App;
