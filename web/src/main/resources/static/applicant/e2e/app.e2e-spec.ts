import { ApplicantPage } from './app.po';

describe('applicant App', () => {
  let page: ApplicantPage;

  beforeEach(() => {
    page = new ApplicantPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
