import React from 'react';
import BoxCategoryR from './BoxCategoryR';
import { SortingBoxContainer } from './style';

const SortingBoxR = () => {
	return (
		<SortingBoxContainer>
			<BoxCategoryR />
			<div>내가 쓴 댓글</div>
		</SortingBoxContainer>
	);
};

export default SortingBoxR;
