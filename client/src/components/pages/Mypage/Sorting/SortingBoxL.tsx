import React from 'react';
import BoxCategoryL from './BoxCategoryL';
import { SortingBoxContainer } from './style';

const SortingBoxL = () => {
	return (
		<SortingBoxContainer>
			<BoxCategoryL />
			<div>좋아요 누른 글</div>
		</SortingBoxContainer>
	);
};

export default SortingBoxL;
