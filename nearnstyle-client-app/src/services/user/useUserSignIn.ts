import { useMutation } from 'react-query';
import axios from 'axios';
import { BASE_URL } from '../../common/constants';

const userSignIn = async ({ mobileNumber }: { mobileNumber: number }) => {
    const { data } = await axios({
        method: 'post',
        url: `${BASE_URL}otp/generate?mobilenumber=${mobileNumber}`,
        headers: {
            Accept: 'application/json',
        }
    });
    return data;
};

export const useUserSignIn = () => useMutation(userSignIn);