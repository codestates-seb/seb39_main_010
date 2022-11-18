import { BasicButton } from 'components/common';
import React from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';

const Banner = () => {
	return (
		<BannerContainer>
			<h1>면접에서 받은 질문 어떻게 답변하셨나요?</h1>
			<p>취업을 위한 면접 준비 Weply에서 함께 해요.</p>
			<p>좋은 질문이 있다면 아래 버튼을 클릭하여 작성해주세요!</p>
			<BasicButton>면접 질문 작성하기</BasicButton>
		</BannerContainer>
	);
};

export default Banner;

const BannerContainer = styled.div`
	width: 100%;
	height: 400px;
	background-color: ${theme.colors.blue100};
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

	& button {
		padding: 15px 50px;
		border-radius: 0;
		width: auto;
		font-size: 18px;
		font-weight: normal;
		height: 52px;
		margin-top: 50px;
	}
`;
