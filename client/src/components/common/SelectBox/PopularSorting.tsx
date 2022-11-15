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

const Filtering = () => {
	function selectBoxchange(event: React.FormEvent<HTMLSelectElement>) {
		const value = event.currentTarget.value; // 선택 옵션 잘나옴
		console.log(value);
		return value;
	}

	return (
		<SortingContainer>
			<form>
				<select onChange={selectBoxchange}>
					<option>최신순</option>
					<option>좋아요순</option>
				</select>
			</form>
		</SortingContainer>
	);
};
export default Filtering;
