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
		font-weight: 600;
		font-size: 36px;
	}
	p {
		margin-top: 15px;

		font-weight: 400;
		font-size: 20px;
	}
`;

const Intro: React.FC<{ title: string; content: string }> = (props) => {
	return (
		<IntroContainer>
			<div>
				<h2>{props.title}</h2>
				<p>{props.content}</p>
			</div>
		</IntroContainer>
	);
};
export default Intro;
