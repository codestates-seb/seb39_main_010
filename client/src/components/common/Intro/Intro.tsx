import React from 'react';
import styled from 'styled-components';

export const IntroContainer = styled.div`
	display: flex;
	div {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;

		width: 1200px;
		height: 200px;
		margin-top: 2rem;

		background: #eff3fd;
		border-radius: 29px;
	}

	h2 {
		font-style: normal;
		font-weight: 600;
		font-size: 36px;
		line-height: 143%;
	}
	p {
		font-style: normal;
		font-weight: 400;
		font-size: 20px;
		line-height: 143%;
	}
`;

const Intro = () => {
	return (
		<IntroContainer>
			<div>
				<h2>스터디 모집</h2>
				<p>함께 성장할 스터디를 모집해보세요</p>
			</div>
		</IntroContainer>
	);
};
export default Intro;
