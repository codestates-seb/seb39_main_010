import React, { ChangeEvent, useState } from 'react';
import styled from 'styled-components';
import { AiFillHeart, AiOutlineHeart } from 'react-icons/ai';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { QuestionComment } from 'components/pages/Question/QuestionCard';
import { useRecoilValue } from 'recoil';
import { userAtom } from 'recoil/atom';
import {
	deleteCommentApi,
	postQuestionCommentLikeApi,
	putCommentApi,
} from 'apis/authApiClient';
import { DefaultCommentProps } from './Comments';

const CommentContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;

	margin-bottom: 25px;

	.nameandtext {
		display: flex;
		flex-direction: column;
		gap: 6px;

		width: 100%;

		div:nth-child(1) {
			font-weight: bold;
		}

		div:nth-child(2) {
			color: #4d4d4d;
		}

		.info {
			display: flex;
			flex-direction: row;
			align-items: center;
			gap: 8px;
			span {
				color: #4d4d4d;
			}
		}
	}

	input {
		font-size: 16px;
		border: 1px solid #d9d9d9;
		padding: 0.5rem 1rem;
		border-radius: 1rem;
	}

	.dot {
		width: 3px;
		height: 3px;

		border-radius: 100%;
		background-color: #d9d9d9;
	}
`;

interface CommentProps extends DefaultCommentProps {
	answer?: QuestionComment;
}

const Comment = ({ type, answer, id, setData, getDataApi }: CommentProps) => {
	const user = useRecoilValue(userAtom);
	const [isEdit, setIsEdit] = useState(false);
	const [editedComment, setEditedComment] = useState(answer?.comment);

	const editComment = async () => {
		putCommentApi(type, editedComment, id, answer?.answerId)
			.then(() => getDataApi(id))
			.then((res) => {
				setData(res.data);
				setIsEdit(!isEdit);
			});
	};

	const removeComment = async () => {
		if (window.confirm('정말 댓글을 삭제하시겠습니까?')) {
			deleteCommentApi(type, id, answer?.answerId)
				.then(() => getDataApi(id))
				.then((res) => {
					setData(res.data);
				});
		}
	};

	const likeComment = () => {
		postQuestionCommentLikeApi(id, String(answer?.answerId))
			.then(() => getDataApi(id))
			.then((res) => setData(res.data));
	};

	return (
		<CommentContainer>
			{answer?.comment ? (
				<>
					<AvatarImg className="avatar-svg" />
					<div className="nameandtext">
						<div>{answer.nickname}</div>
						{isEdit ? (
							<input
								value={editedComment}
								onChange={(e: ChangeEvent<HTMLInputElement>) =>
									setEditedComment(e.currentTarget.value)
								}
							/>
						) : (
							<div>{answer.comment}</div>
						)}
						<div className="info">
							<span>{answer.createdAt.slice(0, 19).replace('T', ' ')}</span>
							<div className="dot"></div>
							<span onClick={likeComment}>
								{answer.userLike ? (
									<AiFillHeart size={15} />
								) : (
									<AiOutlineHeart size={15} />
								)}
								좋아요 {answer.likeCount}
							</span>
							<div className="dot"></div>
							<span>답글달기</span>
							<div className="dot"></div>
							<span>신고</span>
							{user?.nickname === answer.nickname ? (
								<>
									<div className="dot"></div>
									<span
										onClick={() => {
											isEdit ? editComment() : setIsEdit(!isEdit);
										}}
									>
										{isEdit ? '수정 완료' : '수정'}
									</span>
									<div className="dot"></div>
									<span
										onClick={() => {
											isEdit ? setIsEdit(false) : removeComment();
										}}
									>
										{isEdit ? '수정 취소' : '삭제'}
									</span>
								</>
							) : null}
						</div>
					</div>
				</>
			) : (
				<div>답변을 작성해주세요.</div>
			)}
		</CommentContainer>
	);
};

export default Comment;
