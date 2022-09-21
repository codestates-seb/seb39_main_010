import React from 'react';
import styled from 'styled-components';
import { MdKeyboardArrowDown } from 'react-icons/md';

const AccordionContainer = styled.div`
	display: flex;
	justify-content: space-between;

	width: 1200px;
	height: 144px;
	margin-top: 20px;
	padding: 20px 50px;

	background: #ffffff;
	border: 1px solid #dfdfdf;
	border-radius: 10px;
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
	return (
		<AccordionContainer>
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
		</AccordionContainer>
	);
};

export default Accordion;
