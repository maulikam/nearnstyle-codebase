import React from 'react';

import { Platform } from 'react-native';
import { lightColors, createTheme, ThemeProvider } from '@rneui/themed';
import { StoreSelection } from 'module/store/storeSelection';

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
      <StoreSelection />
    </ThemeProvider>
  );
}

export default App;
