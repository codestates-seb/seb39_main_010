import React from 'react';
import styled from 'styled-components';

const CommentIntroContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin-top: 40px;

	width: 1100px;
	height: 144px;

	background-color: #eff3fd;
	border-radius: 29px;

	h3 {
		font-size: 28px;
		margin-bottom: 10px;
	}
	p {
		font-size: 16px;
	}
`;

const CommentIntro: React.FC<{ title: string; content: string }> = (props) => {
	return (
		<CommentIntroContainer>
			<h3>{props.title}</h3>
			<p>{props.content}</p>
		</CommentIntroContainer>
	);
};

export default CommentIntro;
