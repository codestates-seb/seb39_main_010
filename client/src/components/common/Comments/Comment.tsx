import React, { ChangeEvent, useState } from 'react';
import styled from 'styled-components';
import { BsSuitHeart } from 'react-icons/bs';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { QuestionComment } from 'components/pages/Question/QuestionCard';
import { useRecoilValue } from 'recoil';
import { userAtom } from 'recoil/atom';
import { authApiClient } from 'apis/authApiClient';
import { refreshDeleteApi, refreshPutApi } from 'utils/apiUtilFunctions';
import apiClient from 'apis/apiClient';

const CommentContainer = styled.div`
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

interface Props {
	answer?: QuestionComment;
	id?: string;
}

const Comment = ({ answer, id }: Props) => {
	const user = useRecoilValue(userAtom);
	const [isEdit, setIsEdit] = useState(false);
	const [editedComment, setEditedComment] = useState(answer?.comment);

	const putCommentApi = async () => {
		try {
			const response = await authApiClient.put(
				`/api/v1/questions/${id}/answers/${answer?.answerId}`,
				{
					parentId: null,
					comment: editedComment,
				}
			);

			if (response.status === 200) {
				refreshPutApi(`/api/v1/questions/${id}/answers/${answer?.answerId}`, {
					parentId: null,
					comment: editedComment,
				});
			}
			setIsEdit(!isEdit);
			window.location.reload();

			return response;
		} catch (error) {
			console.log(error);
			throw new Error('질문 작성 실패');
		}
	};

	const deleteCommentApi = async () => {
		try {
			const response = await authApiClient.delete(
				`/api/v1/questions/${id}/answers/${answer?.answerId}`
			);

			if (response.status === 200) {
				refreshDeleteApi(`/api/v1/questions/${id}/answers/${answer?.answerId}`);
			}

			return response;
		} catch (error) {
			console.log(error);
			throw new Error('댓글 삭제 실패');
		}
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
							<span>
								<BsSuitHeart size={15} />
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
											isEdit ? putCommentApi() : setIsEdit(!isEdit);
										}}
									>
										{isEdit ? '수정 완료' : '수정'}
									</span>
									<div className="dot"></div>
									<span
										onClick={() => {
											isEdit ? null : deleteCommentApi();
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
