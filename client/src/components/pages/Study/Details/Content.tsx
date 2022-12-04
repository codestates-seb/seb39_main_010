import React from 'react';
import styled from 'styled-components';
import { BsChatLeftDots, BsSuitHeart } from 'react-icons/bs';

const ContentContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	padding: 18px;

	width: 1100px;
	min-height: 434px;

	border-bottom: 1px solid #b0b0b0;

	.below {
		display: flex;
		gap: 15px;
		div {
			display: flex;
			justify-content: center;
			align-items: center;
			padding: 8px 16px;
			gap: 9px;

			border: 1px solid #c5c5c5;
			border-radius: 19px;
			color: #525252;
		}
	}
`;

const Content = () => {
	return (
		<ContentContainer>
			<div>스터디에 관련된 작성자의 자유로운 글 내용 . . .</div>
			<div className="below">
				<div>
					<BsSuitHeart size={20} />
					<span>12</span>
				</div>
				<div>
					<BsChatLeftDots size={20} />
					<span>5</span>
				</div>
			</div>
		</ContentContainer>
	);
};

export default Content;
