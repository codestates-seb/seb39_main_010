import React from 'react';
import styled from 'styled-components';
import Title from 'components/pages/Study/Details/Title';
import Content from 'components/pages/Study/Details/Content';
import CommentIntro from 'components/common/Comments/CommentIntro';

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
				<CommentIntro
					title={`함께 스터디를 하고 싶다면 이야기를 나누어 보세요.`}
					content={`비방글 혹은 글 내용과 상관없는 댓글을 작성할 시 삭제될 수 있습니다.`}
				/>
				{/* <CommentInput /> */}
				{/* <Comments /> */}
			</InnerContainer>
		</StudyDetailContainer>
	);
};

export default StudyDetail;
