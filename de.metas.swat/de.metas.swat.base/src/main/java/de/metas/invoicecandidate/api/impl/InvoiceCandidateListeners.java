package de.metas.invoicecandidate.api.impl;

/*
 * #%L
 * de.metas.swat.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.adempiere.util.Check;

import de.metas.adempiere.model.I_C_InvoiceLine;
import de.metas.invoicecandidate.api.IInvoiceCandidateListeners;
import de.metas.invoicecandidate.api.IInvoiceLineRW;
import de.metas.invoicecandidate.model.I_C_Invoice;
import de.metas.invoicecandidate.model.I_C_Invoice_Candidate;
import de.metas.invoicecandidate.spi.IInvoiceCandidateListener;

public class InvoiceCandidateListeners implements IInvoiceCandidateListeners
{
	private final CopyOnWriteArrayList<IInvoiceCandidateListener> listeners = new CopyOnWriteArrayList<>();

	@Override
	public void addListener(IInvoiceCandidateListener listener)
	{
		Check.assumeNotNull(listener, "listener not null");
		listeners.addIfAbsent(listener);
	}

	@Override
	public void onBeforeInvoiceLineCreated(final I_C_InvoiceLine invoiceLine, final IInvoiceLineRW fromInvoiceLine, final List<I_C_Invoice_Candidate> fromCandidates)
	{
		for (final IInvoiceCandidateListener listener : listeners)
		{
			listener.onBeforeInvoiceLineCreated(invoiceLine, fromInvoiceLine, fromCandidates);
		}
	}

	@Override
	public void onBeforeInvoiceComplete(final I_C_Invoice invoice, final List<I_C_Invoice_Candidate> fromCandidates)
	{

		for (final IInvoiceCandidateListener listener : listeners)
		{
			listener.onBeforeInvoiceComplete(invoice, fromCandidates);
		}
	}

}
