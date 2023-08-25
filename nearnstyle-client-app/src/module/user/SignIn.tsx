import React, { useState } from 'react';
import {
    TextInput,
    View,
    useColorScheme,
    StyleSheet,
    Button,
} from 'react-native';
import {
    Colors,
} from 'react-native/Libraries/NewAppScreen';
import { useUserSignIn } from '../../services/user/useUserSignIn';
function SignIn(): JSX.Element {
    const [mobileNumber, setMobileNumber] = useState('');
    const isDarkMode = useColorScheme() === 'dark';

    const userSignIn = useUserSignIn();
    console.log(userSignIn)
    const onContintueClick = () => {
        userSignIn.mutate({ mobileNumber: parseInt(mobileNumber) })
    }

    return (
        <View style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
        }}>
            <TextInput
                style={styles.input}
                placeholder="Phone number"
                onChangeText={(value) => setMobileNumber(value)}
                value={mobileNumber}
                keyboardType="numeric"
            />
            <View
                style={styles.btn}>
                <Button
                    title="Continuee"
                    onPress={onContintueClick}
                />
            </View>
        </View>
    )
}
const styles = StyleSheet.create({
    input: {
        height: 40,
        margin: 12,
        borderWidth: 1,
        padding: 10,
    },
    btn: {
        paddingStart: 10,
        paddingEnd: 10
    }
});


export default SignIn;