import React from 'react';
import styled from 'styled-components';
import { RiArrowDropDownLine } from 'react-icons/ri';

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
				<select name="color">
					<option value="red">최신순</option>
					<option value="blue">오래된순</option>
					<option value="black">인기순</option>
				</select>
			</form>
		</SortingContainer>
	);
};
export default Sorting;
