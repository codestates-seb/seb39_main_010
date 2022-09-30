import { Introduction, MyProfile } from 'components/pages';
import React from 'react';
import styled from 'styled-components';

export const MypageContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const Mypage = () => {
	return (
		<MypageContainer>
			<Introduction />
			<MyProfile />
		</MypageContainer>
	);
};

export default Mypage;
