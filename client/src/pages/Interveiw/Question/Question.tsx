import React, { useCallback, useEffect, useState } from 'react';
import Banner from 'components/pages/Question/Banner';
import styled from 'styled-components';
import { FilterAndSearchBar } from 'components/pages';
import QuestionCards from 'components/pages/Question/QuestionCards';
import { getQuestionListApi } from 'apis/apiClient';
import { Question } from 'components/pages/Question/QuestionCard';
import LoadingSpinner from 'components/common/LoadingSpinner/LoadingSpinner';
import useInfiniteScroll from 'hooks/useInfiniteScroll';

const QuestionPage = () => {
	const [questionList, setQuestionList] = useState<Question[]>([]);
	const [page, setPage] = useState(1);
	const [lastPage, setLastPage] = useState(100);

	const getNextPageData = useCallback(async () => {
		if (page <= lastPage) {
			await getQuestionListApi(page).then((res) => {
				setQuestionList(([...prev]) => [...prev, ...res.content]);
				setPage(page + 1);
				setLastPage(res.totalPages - 1);
			});
		}
	}, []);

	const setObservationTarget = useInfiniteScroll(getNextPageData);

	useEffect(() => {
		getQuestionListApi(0).then((res) => {
			setQuestionList(res.content);
			setLastPage(res.totalPages - 1);
		});
	}, []);

	if (!questionList.length) return <LoadingSpinner />;
	return (
		<QuestionContainer>
			<Banner />
			<ContentContainer>
				<FilterAndSearchBar
					setDataList={setQuestionList}
					placeholder="궁금한 내용을 찾아보세요."
					url="/interview/question/write"
				/>
				<QuestionCards questionList={questionList} />
				{page <= lastPage ? (
					<Intersection ref={setObservationTarget}></Intersection>
				) : (
					<div></div>
				)}
			</ContentContainer>
		</QuestionContainer>
	);
};

export default QuestionPage;

const QuestionContainer = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	width: 100%;
	height: 100%;
`;

const ContentContainer = styled.div`
	max-width: 1200px;
`;

const Intersection = styled.div`
	width: 100%;
	height: 20px;
`;
