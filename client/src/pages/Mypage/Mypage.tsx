import { getUserProfile } from 'apis/apiClient';
import LoadingSpinner from 'components/common/LoadingSpinner/LoadingSpinner';
import { Introduction } from 'components/pages';
import React, { useEffect, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { userAtom } from 'recoil/atom';
import styled from 'styled-components';

export const MypageContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: center;
	padding: 100px 0;
`;

export interface UserInfo {
	memberId: number;
	nickname: string;
	email: string;
	picture: string;
	favoriteCompany?: string;
	selfIntroductions?: string;
}

const Mypage = () => {
	const user = useRecoilValue(userAtom);
	const [userInfo, setUserInfo] = useState<UserInfo>();

	useEffect(() => {
		getUserProfile(user?.memberId).then((res) => setUserInfo(res));
	}, []);

	if (!userInfo) return <LoadingSpinner />;
	return (
		<MypageContainer>
			<Introduction
				userInfo={userInfo}
				id={user?.memberId}
				setUserInfo={setUserInfo}
			/>
		</MypageContainer>
	);
};

export default Mypage;
