export class Candidate {
  id: number = 0;
  email: string = '';
  phone: string = '';
  resumeUrl: string = ''; // Now a URL (e.g., "/attachments/resume_123.pdf")
  applicationDate: string = '';
  status: string = '';
  jobId: number | null = null;
  jobTitle?: string;

  constructor() {
    this.applicationDate = new Date().toISOString().slice(0, 16);
    this.status = 'applied';
  }
}