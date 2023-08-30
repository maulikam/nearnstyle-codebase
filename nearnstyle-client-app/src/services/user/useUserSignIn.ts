import { useMutation } from 'react-query';
import axios from 'axios';
import { BASE_URL } from '../../common/constants';

const userSignIn = async ({ mobileNumber }: { mobileNumber: number }) => {
    const url = `${BASE_URL}otp/generate?mobilenumber=${mobileNumber}`;
    console.log(url)
    const { data } = await axios({
        method: 'post',
        url,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    });
    return data;
};

export const useUserSignIn = () => useMutation(userSignIn);