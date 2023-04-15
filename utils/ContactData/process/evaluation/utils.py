from cornac import Experiment
import datetime
import numpy as np

class ExperimentResult(list):
    """
    Result Class for an Experiment. A list of :obj:`cornac.experiment.Result`. 
    """

    def __str__(self):
        NUM_FMT = "{:.4f}"
        headers = list(self[0].metric_avg_results.keys())
        data, index = [], []
        for r in self:
            data.append([NUM_FMT.format(r.metric_avg_results[m]) for m in headers])
            index.append(r.model_name)
        return self._table_format(data, headers, index, h_bars=[1])

    
    def _table_format(data, headers=None, index=None, extra_spaces=0, h_bars=None):
        if headers is not None:
            data.insert(0, headers)
        if index is not None:
            index.insert(0, "")
            for idx, row in zip(index, data):
                row.insert(0, idx)

        column_widths = np.asarray([[len(str(v)) for v in row] for row in data]).max(axis=0)

        row_fmt = (
            " | ".join(["{:>%d}" % (w + extra_spaces) for w in column_widths][1:]) + "\n"
        )
        if index is not None:
            row_fmt = "{:<%d} | " % (column_widths[0] + extra_spaces) + row_fmt

        output = ""
        for i, row in enumerate(data):
            if h_bars is not None and i in h_bars:
                output += row_fmt.format(
                    *["-" * (w + extra_spaces) for w in column_widths]
                ).replace("|", "+")
            output += row_fmt.format(*row)
        return output


class CVExperimentResult(ExperimentResult):
    """
    Result Class for a cross-validation Experiment.
    """

    def __str__(self):
        print("===================================================")

        return "\n".join([r.__str__() for r in self])