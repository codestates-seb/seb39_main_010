import React, { useEffect, useState } from 'react';
import { getQuestionApi } from 'apis/apiClient';
import { useNavigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { AiOutlineEye, AiFillHeart, AiOutlineHeart } from 'react-icons/ai';
import { BiCommentDetail } from 'react-icons/bi';
import LoadingSpinner from 'components/common/LoadingSpinner/LoadingSpinner';
import { Question } from 'components/pages/Question/QuestionCard';
import { useRecoilValue } from 'recoil';
import { userAtom } from 'recoil/atom';
import { deleteQuestionApi, postQuestionLikeApi } from 'apis/authApiClient';
import CommentIntro from 'components/common/Comments/CommentIntro';
import QuestionCommentInput from 'components/common/Comments/QuestionCommentInput';
import Comments from 'components/common/Comments/Comments';

const QuestionDetail = () => {
	const { id } = useParams();
	const [data, setData] = useState<Question>();
	const navigate = useNavigate();
	const user = useRecoilValue(userAtom);

	const confirmDelete = () => {
		if (window.confirm('정말로 삭제하시겠습니까?')) {
			deleteQuestionApi(id).then(() => navigate('/'));
		}
	};

	const handleLikeClick = () => {
		postQuestionLikeApi(id)
			.then(() => getQuestionApi(id))
			.then((res) => setData(res.data));
	};

	useEffect(() => {
		getQuestionApi(id).then((res) => setData(res.data));
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
								<span onClick={confirmDelete}>삭제</span>
							</>
						) : null}
					</div>
				</AuthorInfo>
				<Contents>
					<p>{data.content}</p>
					<LikeContainer>
						<span onClick={handleLikeClick}>
							{data.userLike ? (
								<AiFillHeart className="svg heart-svg" />
							) : (
								<AiOutlineHeart className="svg heart-svg" />
							)}
							{data.likeCount}
						</span>
						<span>
							<BiCommentDetail className="svg comment-svg" />
							{data.answers.length}
						</span>
					</LikeContainer>
				</Contents>
				<CommentIntro
					title="면접 질문에 대한 여러분의 답변을 들려주세요."
					content="비방글 혹은 글 내용과 상관 없는 댓글을 작성할 시 삭제될 수 있습니다."
				/>
				<QuestionCommentInput
					getDataApi={getQuestionApi}
					id={id}
					type={'questions'}
					setData={setData}
				/>
				<Comments
					setData={setData}
					getDataApi={getQuestionApi}
					type={'questions'}
					comments={data.answers}
					id={id}
				/>
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
