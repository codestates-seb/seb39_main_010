import React from 'react';
import styled from 'styled-components';
import { MdKeyboardArrowDown } from 'react-icons/md';
import { theme } from 'styles/theme';
import { BasicButton } from 'components/common';

const AccordionContainer = styled.div`
	display: flex;
	flex-direction: column;
	padding: 20px 50px;
	margin-top: 20px;

	width: 1200px;

	background: #ffffff;
	border: 1px solid #dfdfdf;
	border-radius: 10px;

	> div:nth-child(1) {
		display: flex;
		justify-content: space-between;

		height: 144px;
	}

	> div:nth-child(2) {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		padding: 20px 0 10px 0;

		min-height: 200px;

		border-top: ${theme.colors.gray200} 1px solid;
		p {
			color: #949494;
		}
		> div {
			display: flex;
			justify-content: center;
			button {
				width: 217px;
				height: 51.19px;

				font-size: 18px;
				background-color: ${theme.colors.gray200};
				color: ${theme.colors.gray700};

				border: none;
			}
		}
	}
`;

const LeftSide = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
`;
const RightSide = styled.div`
	display: flex;
	align-items: center;

	span {
		margin-right: 30px;

		font-weight: 400;
		color: #5e5e5e;
	}

	div {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 40px;
		height: 40px;

		background: #f7f7f7;
	}
`;

const Sorting = styled.div`
	color: #424242;
`;
const CompanyName = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	h3 {
		font-weight: bold;
		font-size: 24px;
	}
`;
const Accordion = () => {
	const parentRef = React.useRef<HTMLDivElement>(null);

	return (
		<AccordionContainer>
			<div>
				<LeftSide>
					<Sorting>
						<span>2022년 | </span>
						<span>IT/인터넷 | </span>
						<span>신입</span>
					</Sorting>
					<CompanyName>
						<h3>회사명</h3>
					</CompanyName>
				</LeftSide>
				<RightSide>
					<span>2022.09.16</span>
					<div>
						<MdKeyboardArrowDown size={30} />
					</div>
				</RightSide>
			</div>

			<div ref={parentRef}>
				<p>면접 후기와 관련된 내용을 자유롭게 적어주세요</p>
				<div>
					<BasicButton>자세히 보기</BasicButton>
				</div>
			</div>
		</AccordionContainer>
	);
};

export default Accordion;
