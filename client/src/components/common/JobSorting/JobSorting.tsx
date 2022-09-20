import React from 'react';
import styled from 'styled-components';

export const SortingContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 9px 16px;
	margin-left: 15px;
	gap: 6px;

	height: 46px;

	background: #f9f9fc;
	border: 1px solid #cfcfcf;
	border-radius: 22px;
	select {
		float: right;
		padding: 0;
		background: none;
		border: none;
		outline: none;
		color: #000000;
		font-size: 15px;
		line-height: 40px;
	}
`;

const Sorting = () => {
	return (
		<SortingContainer>
			<form>
				{/* <RiArrowDropDownLine size={20} /> */}
				<select name="job">
					<option value="all">직무전체</option>
					<option value="sales">영업/고객상담</option>
					<option value="business">경영/사무</option>
					<option value="marketing">마케팅/광고/홍보</option>
					<option value="produce">생산/제조</option>
					<option value="research">연구개발/설계</option>
					<option value="internet">IT/인터넷</option>
					<option value="service">서비스</option>
					<option value="trade">무역/유통</option>
					<option value="medical">의료</option>
					<option value="structure">건설</option>
					<option value="education">교육</option>
					<option value="design">디자인</option>
					<option value="profession">전문/특수직</option>
					<option value="media">미디어</option>
				</select>
			</form>
		</SortingContainer>
	);
};
export default Sorting;
