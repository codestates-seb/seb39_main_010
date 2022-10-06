import React, { useEffect } from 'react';
import apiClient from 'apis/apiClient';
import { questionDummy } from 'assets/data/questionDummy'; // 삭제 필요
import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { AiOutlineEye } from 'react-icons/ai';
import { BiCommentDetail, BiHeart } from 'react-icons/bi';

const QuestionDetail = () => {
	const { id } = useParams();
	const data = questionDummy.filter((el) => el.questionId === +id!)[0];

	const getQuestionDetail = async () => {
		try {
			const response = await apiClient.get('/api/v1/questions/1');
			console.log(response);
		} catch (error) {
			console.log(error);
		}
	};

	// useEffect(() => {
	// 	getQuestionDetail();
	// }, []);

	return (
		<QuestionDetailContainer>
			<InnerContainer>
				<Tags>
					<span className="category">#{data.category}</span>
					<span className="tag">#{data.tag[0]}</span>
				</Tags>
				<h1>{data.title}</h1>
				<AuthorInfo>
					<div className="author">
						<AvatarImg className="avatar-svg" />
						<span className="nickname">{data.nickname}</span>
						<span>{data.createdAt}</span>
					</div>
					<div className="view">
						<AiOutlineEye className="view-svg" />
						<span className="view-count">{data.viewCount}</span>
						<span>신고</span>
					</div>
				</AuthorInfo>
				<Contents>
					<p>{data.content}</p>
					<LikeContainer>
						<span>
							<BiHeart className="svg heart-svg" />
							{data.likeCount}
						</span>
						<span>
							<BiCommentDetail className="svg comment-svg" />
							{data.answers.length}
						</span>
					</LikeContainer>
				</Contents>
			</InnerContainer>
		</QuestionDetailContainer>
	);
};

export default QuestionDetail;

const QuestionDetailContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
`;

const InnerContainer = styled.div`
	max-width: 1200px;
	margin-top: 70px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	border: 1px solid crimson;

	& h1 {
		font-size: 2rem;
	}
`;

const Tags = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	margin-bottom: 20px;

	& span {
		padding: 10.5px 20px;
		font-size: 1rem;
		border-radius: 1.5rem;
		color: ${theme.colors.gray800};
	}

	& .category {
		background-color: ${theme.colors.blue100};
		margin-right: 12px;
	}

	& .tag {
		background-color: ${theme.colors.green100};
	}
`;

const AuthorInfo = styled.div`
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 4px;
	margin: 1.5rem 0;
	font-size: 18px;

	& span {
		margin-left: 1.5rem;
		/* color: #535353; */ // gray500 적용
		color: ${theme.colors.gray500};
	}

	& .author,
	.view {
		display: flex;
		align-items: center;
	}

	& .nickname {
		margin-left: 12px;
		color: black;
	}

	& .avatar-svg {
		width: 50px;
		height: 50px;
	}

	& .view-svg {
		width: 24px;
		height: 24px;
		color: ${theme.colors.gray500};
	}

	& .view-count {
		margin-left: 2px;
	}
`;

const Contents = styled.div`
	border: 1px solid blue;
	width: 100%;
	padding: 1rem 18px;
	display: flex;
	flex-direction: column;

	& p {
		margin-bottom: 200px;
	}
`;

const LikeContainer = styled.div`
	display: flex;

	& span {
		display: flex;
		justify-content: center;
		align-items: center;
		border: 1px solid #c5c5c5;
		color: #525252;
		padding: 8px 1rem;
		border-radius: 1.5rem;
		font-size: 18px;
		margin-right: 1rem;
	}

	& .svg {
		margin-right: 9px;
	}
`;
