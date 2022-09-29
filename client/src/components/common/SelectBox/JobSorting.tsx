import React from 'react';
import { SortingContainer } from './PopularSorting';

const JobSorting = () => {
	return (
		<SortingContainer>
			<form>
				<select>
					<option>직무전체</option>
					<option>영업/고객상담</option>
					<option>경영/사무</option>
					<option>마케팅/광고/홍보</option>
					<option>생산/제조</option>
					<option>연구개발/설계</option>
					<option>IT/인터넷</option>
					<option>서비스</option>
					<option>무역/유통</option>
					<option>의료</option>
					<option>건설</option>
					<option>교육</option>
					<option>디자인</option>
					<option>전문/특수직</option>
					<option>미디어</option>
				</select>
			</form>
		</SortingContainer>
	);
};
export default JobSorting;
