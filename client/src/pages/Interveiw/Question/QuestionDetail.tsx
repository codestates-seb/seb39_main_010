import React, { useEffect, useState } from 'react';
import apiClient, { getQuestionApi } from 'apis/apiClient';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { AiOutlineEye } from 'react-icons/ai';
import { BiCommentDetail, BiHeart } from 'react-icons/bi';
import Comments from 'components/common/Comments/Comments';
import LoadingSpinner from 'components/common/LoadingSpinner/LoadingSpinner';
import { Question } from 'components/pages/Question/QuestionCard';
import { useRecoilValue } from 'recoil';
import { userAtom } from 'recoil/atom';
import { authApiClient, deleteQuestionApi } from 'apis/authApiClient';
import { refreshDeleteApi } from 'utils/apiUtilFunctions';

const QuestionDetail = () => {
	const { id } = useParams();
	const [data, setData] = useState<Question>();
	const navigate = useNavigate();
	const user = useRecoilValue(userAtom);

	useEffect(() => {
		getQuestionApi(id).then((res) => setData(res.data));
		console.log(data);
	}, []);

	if (!data) return <LoadingSpinner />;
	return (
		<QuestionDetailContainer>
			<InnerContainer>
				<Tags>
					<span className="category">#{data.category}</span>
					<span className="tag">#{data.tag}</span>
				</Tags>
				<h1>{data.title}</h1>
				<AuthorInfo>
					<div className="author">
						<AvatarImg className="avatar-svg" />
						<span className="nickname">{data.nickname}</span>
						<span>{data.createdAt.slice(0, 19).replace('T', ' ')}</span>
					</div>
					<div className="view">
						<AiOutlineEye className="view-svg" />
						<span className="view-count">{data.viewCount}</span>
						<span>신고</span>
						{user?.nickname === data.nickname ? (
							<>
								{' '}
								<span
									onClick={() => navigate(`/interview/question/edit/${id}`)}
								>
									수정
								</span>
								<span
									onClick={() => {
										deleteQuestionApi(id);
										navigate('/');
									}}
								>
									삭제
								</span>
							</>
						) : null}
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
				<Comments comments={data.answers} id={id} />
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
	width: 1200px;
	margin-top: 70px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

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
