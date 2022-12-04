import React from 'react';
import styled from 'styled-components';
import Title from 'components/pages/Study/Details/Title';
import Content from 'components/pages/Study/Details/Content';
import Comments from 'components/common/Comments/Comments';

const StudyDetailContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;

	font-size: 18px;
`;

const InnerContainer = styled.div`
	max-width: 1200px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

const StudyDetail = () => {
	return (
		<StudyDetailContainer>
			<InnerContainer>
				<Title />
				<Content />
				<Comments />
			</InnerContainer>
		</StudyDetailContainer>
	);
};

export default StudyDetail;
