import React from 'react';
import styled from 'styled-components';
import { BsChatLeftDots, BsSuitHeart } from 'react-icons/bs';
import { RiSendPlaneFill } from 'react-icons/ri';
import Title from 'components/pages/Study/Details/Title';
import Content from 'components/pages/Study/Details/Content';
import Comments from 'components/pages/Study/Details/Comments';

const StudyDetailContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	font-size: 18px;

	.profilepic {
		display: flex;
		justify-content: center;
		align-items: center;

		width: 50px;
		height: 50px;

		background-color: #d9d9d9;
		border-radius: 100%;
	}
`;

const StudyDetail = () => {
	return (
		<StudyDetailContainer>
			<Title />
			<Content />
			<Comments />
		</StudyDetailContainer>
	);
};

export default StudyDetail;
